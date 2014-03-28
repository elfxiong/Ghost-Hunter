var mongoose = require('mongoose');

var playerSchema = mongoose.Schema({
	name: String,
	password: String,
	online: Boolean,
	location: {
		x: Number,
		y: Number
	},
	date: { type: Date, default: Date.now}
});

exports.model = mongoose.model('Player', playerSchema);