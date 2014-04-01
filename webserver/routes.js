module.exports = function(app) {
	var handlers = require('./handlers');
	app.get('/', handlers.index);
	app.post('/init', handlers.createPlayer)
}
