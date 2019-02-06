let userRotue = require('./Router/UserRoute');
let panelRoute = require('./Router/PanelRoute');

module.exports = (app) => {
    app.use('/api/User',userRotue());
    app.use('/api/Panel',panelRoute());
}