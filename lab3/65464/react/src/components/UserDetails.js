import React from 'react'
import styled from 'styled-components'
import ReactWordcloud from 'react-wordcloud';
import { Carousel, Descriptions, Avatar } from 'antd';
import { Comment } from 'antd';

const DescriptionCenter = styled(Descriptions)`
    width: 100%;
    margin: 0 auto; 
    text-align: center;
`;

const Twitter = styled.div`
    width: 100%;
    margin: 0 auto; 
    text-align: center;
`;
const Cloud = styled.div`
    width: 100vh;
    height: 70vh;
    margin: 0 auto;
`

const UserDetails = ({ userDetails, lastTwitts }) => {
    const words = lastTwitts.map(o1 => o1.fullText)
        .flatMap(o1 => o1.split(" "))
        .map(text => {
            return {
                text,
                value: Math.floor(Math.random() * (40 - 1 + 1) + 1)
            }
        })

    return (
        <div>
            <UserPrimaryInfo userDetails={userDetails} />
            <UserTwitts userDetails={userDetails} lastTwitts={lastTwitts} />
            <Cloud>
                <ReactWordcloud words={words} />
            </Cloud>
        </div>
    )
}

const UserPrimaryInfo = ({ userDetails }) => {
    const { name, profileImageUrl, screenName, createdAt } = userDetails
    return (
        <DescriptionCenter column={4}>
            <Descriptions.Item label="name">{name}</Descriptions.Item>
            <Descriptions.Item label="screenName">{screenName}</Descriptions.Item>
            <Descriptions.Item label="createdAt">{createdAt}</Descriptions.Item>
            <Descriptions.Item label="image"><Avatar src={profileImageUrl} /></Descriptions.Item>
        </DescriptionCenter>
    )
}

const UserTwitts = ({ userDetails, lastTwitts }) => {
    return (
        <Carousel autoplay>
            {lastTwitts
                .map(o1 => o1.fullText)
                .map((item, index) => {
                    return (
                        <Twitter key={index}>
                            <Comment
                                key={index}
                                author={userDetails.name}
                                avatar={userDetails.profileImageUrl}
                                content={item}
                                datetime={userDetails.createdAt}
                            />
                        </Twitter>
                    )
                })}
        </Carousel>
    )
}

export default UserDetails
