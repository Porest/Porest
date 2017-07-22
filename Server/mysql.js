// Auther : JoMingyu

const mysql = require('mysql');
const connection = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    database: 'porest',
    user: 'root',
    password: ''
});

connection.connect();

module.exports = connection;
