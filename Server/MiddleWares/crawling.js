const cheerio = require('cheerio');
const request = require('request');
const fs = require('fs');

const crawlingMiddleware = (req,res,next) =>{
    let panelsInfo = []
    let page_num = 1
    let url = 'https://kr.enfsolar.com/pv/panel'+page_num
    request(url, (error,response, body) => {
        const $ = cheerio.load(body);
        for(let i = 1 ; i <= 20 ; i++){
            name = $('body > div.container > div > div.enf-pd-list >'+
            ' div.enf-pd-list-main > ul > li:nth-child('+i+') '+
            '> div.enf-pd-list-main-li-right > '+
            'div.row.enf-row > div:nth-child(1) > a').text().split(':')
            console.log(name);
            panelName = name[0].trim() + ":" + name[1].trim()
           
            company = $('body > div.container > div > div.enf-pd-list > '+
            'div.enf-pd-list-main > ul > li:nth-child('+i+') > '+
            'div.enf-pd-list-main-li-right > div.row.enf-row > div:nth-child(1) > div.enf-blue').text().trim();
            type = $('body > div.container > div > div.enf-pd-list > div.enf-pd-list-main > ul >'+
            ' li:nth-child('+i+') > div.enf-pd-list-main-li-right > table >'+
            ' tbody > tr:nth-child(1) >'+
            ' td.enf-yellow').text().trim()
            percent = $('body > div.container > div > div.enf-pd-list > '+
            'div.enf-pd-list-main > ul > li:nth-child('+i+') > div.enf-pd-list-main-li-right'+
            ' > table > tbody > tr:nth-child(1)'+
            ' > td.enf-blue').text().trim()
            range = $('body > div.container > div > div.enf-pd-list >'+
            ' div.enf-pd-list-main > ul > li:nth-child('+i+') >'+
            ' div.enf-pd-list-main-li-right > table > tbody '+
            '> tr.enf-grey-back > td:nth-child(2)').text().trim()
            weight = $('body > div.container > div > div.enf-pd-list '+
            '> div.enf-pd-list-main > ul > li:nth-child('+i+') '+
            '> div.enf-pd-list-main-li-right > table '+
            '> tbody > tr.enf-grey-back > td:nth-child(4)').text().trim()
            contry = $('body > div.container > div > div.enf-pd-list '+
            '> div.enf-pd-list-main > ul > li:nth-child('+i+') > '+
            'div.enf-pd-list-main-li-right > table > tbody '+
            '> tr:nth-child(3) > td:nth-child(2)').text().trim()
            appearance = $('body > div.container > div > div.enf-pd-list '+
            '> div.enf-pd-list-main > ul > li:nth-child('+i+') > '+
            'div.enf-pd-list-main-li-right > table > tbody '+
            '> tr:nth-child(3) > td:nth-child(4)').text().trim( b)
            imgUrl = $('body > div.container > div > div.enf-pd-list > '+
            'div.enf-pd-list-main > ul > li:nth-child('+panelName+') '+
            '> div.enf-pd-list-main-li-left').find('img').attr('data-original')
            let imgOption = {
                method:"GET",
                url:imgUrl,
                encoding: null
            }
            console.log(imgUrl)
            
            request(imgOption).pipe(fs.createWriteStream('./imgPanel/'+i+'.jpg'));
            let data = {name:panelName,type:type,percent:percent,range:range,weight:weight
                ,contry:contry,appearance:appearance,company:company}
            panelsInfo.push(data)
            console.log(data)
        }
      
        res.send(data)        
    })
}

module.exports = crawlingMiddleware;