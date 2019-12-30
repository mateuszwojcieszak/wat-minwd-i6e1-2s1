const express = require('express')
const app = express()
const port = 3000
const rp = require('request-promise');

const { TWITTER_URL } = require('./constants')
const { scrapDataTwitter } = require('./scrapperService')

app.get('/', (req, res) => res.send('Server is on!'))

app.get('/find/:username', (req, res) => {
    const { username } = req.params;
    rp(`${TWITTER_URL}${username}`)
        .then(o1 => {
            const response = scrapDataTwitter(o1)
            res.send(response)
        })
        .catch(o1 => res.send(o1))
})

app.listen(port, () => console.log(`App is listening on port ${port}!`))
