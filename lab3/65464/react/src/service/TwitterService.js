import { userDetails, json as lastTwits } from '../mock/mock'

export const fetchUserDetails = async (formData) => {
    const { username } = formData
    return await fetch(`http://localhost:5000/Twitter/User/${username}`)
        .then(res => res.json())
        .catch(() => JSON.parse(userDetails))
}

export const fetchLastTwittsTest = async (formData) => {
    const { quantity, username } = formData
    const tweetsCount = quantity;
    return await fetch(`http://localhost:5000/Twitter/Tweets/${username}`, { qs: { tweetsCount } })
        .then(res => res.json())
        .catch(() => lastTwits)
}