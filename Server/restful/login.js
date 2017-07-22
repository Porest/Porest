var express = require('express');
var router = express.Router();
var connection= require('../mysql.js');

router.route('/register').post((req, res) => {
    console.log('/register');
    var paramid= req.body.id;
    var parampassword= req.body.password;
    var paramnickname= req.body.nickname;
    var paramregistration_id= req.body.registration_id;
    var paramreceived_like= req.body.received_like;

    var sql;
    sql='SELECT id from account ';
    connection.query(sql, (err, result)=>{
        if(err) {
            throw err;
            res.sendStatus(204);
        }
        if(result.length){
            res.sendStatus(203);
            console.log('이미있는아이디');
        }else{
            sql='INSERT INTO account SET ?';
            var userInput={
                id: paramid,
                password: parampassword,
                registration_id: paramregistration_id,
                received_like: paramreceived_like
            }
            connection.query(sql,userInput,(err,result)=>{
                if(err){
                    throw err;
                    res.sendStatus(204);
                }
                console.log(paramid+'추가 성공');
            });
        }
    });
   
});

router.route('/login').get((req,res)=>{
        
});
router.route('/login').post((req,res)=>{
    console.log('/login');
    var paramid= req.body.id;
    var parampassword= req.body.password;
    
    connection.query('select id from account where id=?',paramid,(err, result)=>{
        if(err){
            throw err;
            res.sendStatus(204);
        }
        connection.query('select id from account where password=?',parampassword,(err, result)=>{
        if(err){
            throw err;
            res.sendStatus(204);
        }
        if(!result){
            console.log('아이디나 비밀번호 오류');
            res.sendStatus('203');
        }else{
            console.log(paramid+'login');
            res.sendStatus('200');
        }
    });
    });
});

module.exports = router;
