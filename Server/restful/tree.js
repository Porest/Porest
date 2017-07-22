// Auther : JoMingyu

const express = require('express');
const router = express.Router();
const mysql = require('../mysql.js');
const util = require('util');

const date = new Date();

router.route('/tree').post((req, res) => {
    // 나무 만들기
    let id = req.body.id;
    let maximumLeaves = req.body.maximum_leaves;
    let currentDate = date.toISOString().replace(/T/, ' ').replace(/\..+/, '');

    mysql.query(`INSERT INTO tree(owner, creation_time, maximum_leaves) VALUES('${id}', '${currentDate}', ${maximumLeaves})`, (err, rows) => {
        if(!err) {
            res.sendStatus(201);
        }
    });
}).get((req, res) => {
    // 나무 리스트
});

module.exports = router;
