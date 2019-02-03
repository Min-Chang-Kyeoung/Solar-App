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
            console.log(err);
            res.send({err:err})
        })
    },

    loginUser : function(req,res) {
        let {id,password} = req.body;
        let secret = req.app.get('jwt-secret');
        let check = (user) => {
            if(!user){
                throw new Error ('user login failed');
            }
            else{
                
                if(user.password === password){
                    let promise = new Promise((resolve,reject) => {
                        jwt.sign({
                            _id:user._id,
                            id:user.id,
                            password:user.password,
                            name:user.name,
                            generator_num:user.generator_num
                        },
                        secret,
                        {
                            expiresIn :'7d',
                            issuer: 'chang-__-',
                            subject:'userInfo'
                        },(err, token) =>{
                            if(err) reject(err);
                            resolve(token);
                            token;
                        }) 
                    })
                    return promise;
                }   
                else{
                    throw new Error ('jwt login failed');
                }
            }
        }
    let respond = (token) => {
        res.json({
            message: 'logged in successfully',
            token
        })
    }

    let onError = (error) => {
        res.status(403).json({
            message: error.message
        })
    }

        User.findOne({id:id}).exec()
        .then(check)
        .then(respond)
        .catch(onError)
    }

}