import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import Tree from 'react-tree-graph';
import 'react-tree-graph/dist/style.css'

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Divider from '@material-ui/core/Divider';


class Buttons extends React.Component {

    state = {
        input: '',
        graphDepth: '',
        email: '-',
        name: '-',
        avatarUrl: '-',
        blog: '-',
        createdAt: '-',
        url: '-',
        repos: '-',
        data: {
            name: 'Parent',
            children: [{
                name: 'Child One'
            }, {
                name: 'Child Two'
            }]
        }
    }

    useStyles = makeStyles(theme => ({
        root: {
            '& > *': {
                margin: theme.spacing(1),
                width: 200,
            },
        },
    }));

    handleChange = event => {
        this.setState({input: event.target.value});
    }

    handleSubmit = event => {
        event.preventDefault();

        const input = {
            input: this.state.input,
            graphDepth: 3
        };
        axios.post(`http://localhost:8080/getUserData`, {input})
            .then(res => {
                console.log(res);
                this.changeData(res.data.data)
                this.changeUserDataThinds(res.data)
            })
    }

    changeData(data2) {
        this.setState({
            data: data2
        })
    }

    changeUserDataThinds(fromRequest){
        this.setState({
            email: fromRequest.email,
            name: fromRequest.name,
            avatarUrl: fromRequest.avatarUrl,
            blog: fromRequest.blog,
            createdAt: fromRequest.createdAt,
            repos: fromRequest.publicRepos,
            url: fromRequest.htmlUrl
        })
    }

    render() {
        return (
            <>
                <form className={this.useStyles.root} noValidate autoComplete="off">
                    <TextField id="standard-basic" label="Github username" onChange={this.handleChange}/>
                </form>
                <br></br>
                <Button variant="contained" color="primary" type="submit" onClick={this.handleSubmit}>
                    Wyszukaj
                </Button>
                <br></br>
                <br></br>
                <br></br>
                <br></br>
                <List>
                    <ListItem>
                        <ListItemText primary={this.state.email} secondary="email"/>
                    </ListItem>
                    <Divider component="li"/>
                    <ListItem>
                        <ListItemText primary={this.state.name} secondary="name"/>
                    </ListItem>
                    <Divider component="li"/>
                    <ListItem>
                        <ListItemText primary={this.state.avatarUrl} secondary="avatar-url"/>
                    </ListItem>
                    <Divider component="li"/>
                    <ListItem>
                        <ListItemText primary={this.state.blog} secondary="blog"/>
                    </ListItem>
                    <Divider component="li"/>
                    <ListItem>
                        <ListItemText primary={this.state.createdAt} secondary="created-at"/>
                    </ListItem>
                    <Divider component="li"/>
                    <ListItem>
                        <ListItemText primary={this.state.url} secondary="url"/>
                    </ListItem>
                    <Divider component="li"/>
                    <ListItem>
                        <ListItemText primary={this.state.repos} secondary="repos count"/>
                    </ListItem>
                    <Divider component="li"/>
                </List>
                <Tree
                    data={this.state.data}
                    height={400}
                    width={400}/>
            </>
        )
    }
}

export default Buttons