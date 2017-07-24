
const express = require('express');
const router = express.Router();

const connection = require('../mysql.js');
const util = require('util');
const date = new Date();
const currentDate = date.toISOString().replace(/T/, ' ').replace(/\..+/, '');

router.route('/forest/add').post((req, res) => {
    var treeIndex = req.body.tree_idx;
    var hasarray = ['hi', 'ho', 'he'];
    var hashIndex;

    connection.query('select is_shared from tree', (err, result) => {
        if (err) {
            console.log(err);
            res.sendStatus(204);
        }
        if (result) {
            connection.query('update tree set is_shared=? where tree_idx=?', [1, treeIndex], (err, result) => {
                if (err) {
                    console.log(err);
                    res.sendStatus(203);
                }
            });
        }
    });
    var forestInput = [treeIndex, currentDate];

    connection.query('insert into forest(tree_idx,creation_time) values(?,?)', forestInput, (err, result) => {
        if (err) {
            console.log(err);
            res.sendStatus(204);
        }

        connection.query('select hash_idx from forest where tree_idx=?', treeIndex, (err, result) => {
            if (err) {
                console.log(err);
                res.sendStatus(204);
            }
            hashIndex = result[0].hash_idx;

            for (var i = 0; i < hasarray.length; i++) {
                var tagsInput = [hashIndex, hasarray[i]];
                connection.query('insert into tags(hash_idx,contant) values(?,?)', tagsInput, (err, result) => {
                    if (err) {
                        console.log(err);
                        res.sendStatus(204);
                    }
                })
                if (i == 3) {
                    res.sendStatus(200);
                }
            }
        });
    });
});
router.route('/forest/random/select').get((req, res) => {
    connection.query('select * from tree where is_shared=?', 1, (err, result) => {
        if (err) {
            console.log(err);
            res.sendStatus(204);
        }
        res.json(result);
        res.sendStatus(200);
    });
});
router.route('/forest/tag/select').post((req, res) => {
    var tag = req.body.contant;
    connection.query('select hash_idx from tags where contant=?', tag, (err, result) => {
        if (err) {
            console.log(err);
            res.sendStatus(204);
        }
        var tagArray = result;

        var treeArray = [];
        for (var i = 0; i < tagArray.length; i++) {
            connection.query('select tree_idx from forest where hash_idx=?', tagArray[i].hash_idx, (err, result) => {
                if (err) {
                    console.log(err);
                    res.sendStatus(204);
                }

                treeArray.push(result[0].tree_idx);
                // connection.query('select ')
                if (i === tagArray.length - 1) {
                    res.status(200).json(treeArray);
                }
            });
        }
    });
});

module.exports = router;
