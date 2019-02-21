let userRotue = require('./Router/UserRoute');
let panelRoute = require('./Router/PanelRoute');
let crawlRoute = require('./MiddleWares/crawling');

module.exports = (app) => {
    app.use('/api/User',userRotue());
    app.use('/api/Panel',panelRoute());
    app.use('/crawldata',crawlRoute);
}