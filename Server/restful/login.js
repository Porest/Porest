var express = require('express');
var router = express.Router();
var connection = require('../mysql.js');
var sql;

router.route('/register').post((req, res) => {
    console.log('/register');
    var paramid = req.body.id;
    var parampassword = req.body.password;
    var paramnickname = req.body.nickname;
    var paramregistration_id = req.body.registration_id;

    console.log(paramid);
    connection.query('select id from account where id=?', paramid, (err, result) => {

        if (err) {
            throw err;
            res.sendStatus(204);
        }
        if (result.length) {
            res.sendStatus(203);
            console.log('이미있는아이디');
        } else {
            var userInput = {
                id: paramid,
                password: parampassword,
                nickname: paramnickname,
                registration_id: paramregistration_id
            };
            connection.query('insert into account set ?', userInput, (err, result) => {
                if (err) {
                    throw err;
                    res.sendStatus(204);
                }
                console.log(paramid + '추가 성공');
                res.sendStatus(200);
            });
        }
    });

}).get((req, res) => {

});

router.route('/login').post((req, res) => {
    console.log('/login');
    var paramid = req.body.id;
    var parampassword = req.body.password;

    connection.query('select id from account where id=?', paramid, (err, result) => {
        if (err) {
            throw err;
            res.sendStatus(204);
        }
        connection.query('select id from account where password=?', parampassword, (err, result) => {
            if (err) {
                throw err;
                res.sendStatus(204);
            }
            if (!result) {
                console.log('아이디나 비밀번호 오류');
                res.sendStatus(203);
            } else {
                console.log(paramid + 'login');
                res.sendStatus(200);
            }
        });
    });
});

module.exports = router;
