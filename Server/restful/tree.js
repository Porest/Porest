// Auther : JoMingyu

const express = require('express');
const router = express.Router();

router.route('/tree').post((req, res) => {
    // 나무 만들기
    let id = req.body.id;
    let maximumLeaves = req.body.maximum_leaves;
}).get((req, res) => {
    // 나무 리스트
});

module.exports = router;
