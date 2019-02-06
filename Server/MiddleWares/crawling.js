const cheerio = require('cheerio');
const request = require('request');

const crawlingMiddleware = (req,res,next) =>{
    let page_num = 1
    let url = 'https://kr.enfsolar.com/pv/panel'
    request(url, (error,response, body) => {
        const $ = cheerio.load(body);
        data = $('body > div.container > div > div.enf-pd-list > div.enf-pd-list-main > ul > li:nth-child(1) > div.enf-pd-list-main-li-right > table > tbody > tr:nth-child(1) > td.enf-yellow')
        console.log(data.text().trim());
        res.send(data.text());        
    })
}

module.exports = crawlingMiddleware;