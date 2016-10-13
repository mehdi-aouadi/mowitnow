// Author : Mehdi AOUADI
// Reused snilyes source code from Github https://github.com/snilyes/mowitnow

var queue = [];
var interval;
var stompClient = null;
var cellWidth = 50;
var leftPadding = 30;
var monitor = null;
$(connect);

function dropFile() {
	var zone = new FileDrop('instructions', {
		input : false
	});
	zone.event('send', function(files) {
		files.each(function(file) {
			file.readData(function(str) {
				zone.el.value = str;
			}, function(e) {
				$("#error-banner").empty().append(
						"<strong>Error</strong>: " + e).show();
			}, 'text');
		});
	});
}
/**
 * STOMP Client connection
 */
function connect() {
	if (stompClient) {
		stompClient.disconnect();
	}

	var socket = new SockJS('/mowitnow');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);

		//Initializing
		stompClient.subscribe('/mowers/init', function(data) {
			$("#error-banner").empty().hide();
			monitor = $.parseJSON(data.body);
			draw();
			receive();
		});

		//Mower updates
		stompClient.subscribe('/mowers/update', function(data) {
			queue.push($.parseJSON(data.body));
			receive();
		});

		//Errors
		stompClient.subscribe('/mowers/error', function(data) {
			$("#error-banner").empty().append(
					"<strong>Error : </strong> " + data.body).show();
			receive();
		});

		//Wait for connection
		$("#start").removeAttr("disabled").removeClass('btn-default').addClass(
				'btn-primary');
		$("#start").text("Start Mowing");
		$("#start").click(function() {
			console.log('Start Mowing');
			start();
			return false;
		});

		dropFile();
	});
}

function receive() {
	stompClient.send("/app/receive", {}, '');
}
/**
 * Start the mowing animation
 */
function start() {
	$("#result").hide();
	var instructions = $("#instructions").val();
	stompClient.send("/app/execute", {}, $("#instructions").val());

	interval = self.setInterval(function() {
		update();
	}, 500);
}

/**
 * Mise à jour de la vue pelouse
 */
function update() {
	var parsedJSON = queue.shift();
	if (parsedJSON) {
		move(parsedJSON);
	} else {
		clearInterval(interval);
		result();
	}
}

/**
 * Draw the Lawn and the Mower(s)
 */
function draw() {
	var lawn = monitor.lawn;
	var mowers = monitor.mowers;
	var width = lawn.width;
	var height = lawn.height;

	if ($("canvas")) {
		$("canvas").remove();
	}
	$(
			'<canvas id="canvas" width="' + (leftPadding + (cellWidth * width))
					+ '" height="' + (leftPadding + (cellWidth * height))
					+ '"></canvas>').appendTo("#canvas-container");

	// Draww the Cells (grass)
	for (var i = 0; i < width; i++) {
		for (var j = 0; j < height; j++) {
			$("canvas").drawImage({
				source : "img/grass.jpg",
				x : leftPadding + i * cellWidth,
				y : j * cellWidth,
				width : cellWidth,
				height : cellWidth,
				fromCenter : false
			});
		}
	}
	
	//Draw the horizontal X axis
	for (var i = 0; i < width; i++) {
		var ctx = document.getElementById('canvas').getContext('2d');
		ctx.font = "15px Arial";
		ctx.fillStyle = "#660033";
		ctx.fillText(i, cellWidth * i + cellWidth / 2 + leftPadding, 20
				+ cellWidth * height);
	}

	//Draw the vertical Y axis
	for (var j = 0; j < height; j++) {
		var ctx = document.getElementById('canvas').getContext('2d');
		ctx.font = "15px Arial";
		ctx.fillStyle = "#660033";
		ctx.fillText(j, 0, cellWidth * (height - j - 1) + (cellWidth / 2));
	}

	//Draw the mowers
	for ( var i in mowers) {
		var mower = mowers[i];
		$("canvas").drawImage({
			source : "img/mower-" + mower.orientation + ".png",
			x : leftPadding + mower.cell.position.x * cellWidth,
			y : (height - mower.cell.position.y - 1) * cellWidth,
			width : cellWidth,
			height : cellWidth,
			fromCenter : false,
			scale : 0.7
		});
	}
}

//Moving the mower
function move(mower) {
	var width = monitor.lawn.width;
	var height = monitor.lawn.height;
	console.debug("Moving the mower : " + mower.id);
	var older = monitor.mowers[mower.id];
	if (older.cell != mower.cell) {
		$("canvas").drawImage({
			source : "img/mowed_grass.jpg",
			x : leftPadding + older.cell.position.x * cellWidth,
			y : (height - older.cell.position.y - 1) * cellWidth,
			width : cellWidth,
			height : cellWidth,
			fromCenter : false
		});
	}

	$("canvas").drawImage({
		source : "img/mowed_grass.jpg",
		x : leftPadding + mower.cell.position.x * cellWidth,
		y : (height - mower.cell.position.y - 1) * cellWidth,
		width : cellWidth,
		height : cellWidth,
		fromCenter : false,
	});

	$("canvas").drawImage({
		source : "img/mower-" + mower.orientation + ".png",
		x : leftPadding + mower.cell.position.x * cellWidth,
		y : (height - mower.cell.position.y - 1) * cellWidth,
		width : cellWidth,
		height : cellWidth,
		fromCenter : false,
		scale : 0.7
	});

	monitor.mowers[mower.id] = mower;
}

//Showing the final mower(s) position(s)
function result() {
	$("#result").empty();
	$("#result").append('<p>Final positions :</p>');
	for ( var i in monitor.mowers) {
		var mower = monitor.mowers[i];
		$("#result").append('<p>Mower ' + increment(i) + ' : ('
						+ (mower.cell.position.x) + ','
						+ (mower.cell.position.y) + ',' + mower.orientation
						+ ')</p>');
	}
	$("#result").show();
}
