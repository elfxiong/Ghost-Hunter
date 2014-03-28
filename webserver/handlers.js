var mongoose = require('mongoose'),
Pin = mongoose.model('Pin');


exports.index = function(req, res) {
	res.send("Welcome to Ghost Hunter game!");
}


exports.createPlayer = function(req, res) {
	var newPin = new Player({ 
		name: req.body.name, 
		password: String,
	});
	newPlayer.save(function(err) {
		if (err) {
			console.log("Error creating player.");
		} else {
			// res.redirect('/somewhere');
		};
	});	
}
