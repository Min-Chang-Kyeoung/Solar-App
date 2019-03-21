const User = require('../Models/User');
const jwt = require('jsonwebtoken');

module.exports = {

    insertUser : function (req,res) {

        let newUser = new User({
            name:req.body.name,
            id:req.body.id,
            password:req.body.password,
            generator_num:req.body.generator_num
        });
        newUser.save()
        .then(user => {
            console.log(user);
            res.send({masseage:'success'});
        })
        .catch(err => {
            res.send({err:err})
        })
    },

    loginUser : function(req,res) {
    let id = req.body.id;
    let pw = req.body.pw;
    const secret = req.app.get('jwt-secret');
    const onError = (error) => {
        res.status(403).json({
            message: error.message
        })
    }
    
    const check = (user) =>{    
        if(!user){
            throw new Error('Login failed');
        }else{
        const promise = new Promise((resolve,reject) =>{
                jwt.sign(
                    {
                    _id:user._id,
                    id:user.id,
                    },
                    secret,{
                        expiresIn: '7d',
                        issuer:'chang-__-',
                        subject:'userInfo'
                    },(err,token) =>{
                    if(err) reject(err)
                    resolve({token:token,name:user.name
                        ,hardware:user.generator_num})
                })
            })
            return promise;
        }   
    }
    
    const respond = (info) =>{
       console.log(info);
        res.json({
            info
        })
    }

    User.findOne({id:id,password:pw}).exec()
        .then(check)
        .then(respond)
        .catch(onError)
    }

}