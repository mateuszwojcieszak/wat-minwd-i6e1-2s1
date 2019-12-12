import { userDetails, lastTwits, testTwit } from '../mock/mock'

export const fetchUserDetails = async (formData) => {
    // return await fetch('url.com/Twitter')
    //     .then(res => res.json());
    return new Promise(resolve => {
        setTimeout(() => resolve(JSON.parse(userDetails)), 1200)
    })
}

export const fetchLastTwittsTest = async (formData) => {
    const { quantity } = formData
    const tweetsCount = quantity;
    // return await fetch('url.com/Twitter/TestTweet'})
    //     .then(res => res.json());
    return new Promise(resolve => {
        setTimeout(() => resolve(testTwit), 1200)
    })
}

export const fetchLastTwitts = async (formData) => {
    const { quantity } = formData
    const tweetsCount = quantity;
    //return await fetch('url.com/Twitter/Tweets', { qs: { tweetsCount } })
    //     .then(res => res.json());
    return new Promise(resolve => {
        setTimeout(() => resolve(lastTwits), 1200)
    })
}