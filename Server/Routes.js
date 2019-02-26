let userRotue = require('./Router/UserRoute');
let panelRoute = require('./Router/PanelRoute');
let crawlRoute = require('./MiddleWares/crawling');
let imgRoute = require('./Router/ImageRoute');

module.exports = (app) => {
    app.use('/api/User',userRotue());
    app.use('/api/Panel',panelRoute());
    app.use('/api/Img',imgRoute());
    app.use('/crawldata',crawlRoute);
}