var userRotue = require('./Router/UserRoute');

module.exports = (app) => {
    app.use('/api/User',userRotue());
}