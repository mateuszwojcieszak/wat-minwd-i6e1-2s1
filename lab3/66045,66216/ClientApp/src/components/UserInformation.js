import React, { Component } from 'react';

export class UserInformation extends Component{
    static displayName = UserInformation.name;

    constructor(props){
        super(props);
        this.state = {informations: null, loading: true};
    }

    componentDidMount(){
        this.populateInformations();
    }

    static renderInformations(informations){
        return(
            <div>
                <div class="headers">
                    <h1>{informations.login}</h1>
                    <h5>{informations.name}</h5>
                    <img src={informations.avatar_url} class="img_avatar"></img>
                </div>
                <div class="d-flex justify-content-center">
                <p>
                    <span>{informations.location}</span>
                    <span>Followers : {informations.followers}</span>
                    <span>Following : {informations.following}</span>
                </p>
                </div>
                
                {informations.reposArray.map(repo =>
                    <div class = "d-flex justify-content-center" styles="margin-top:7%;">
                        <p>
                            <span><h5>{repo.name}</h5></span>
                            <span>{repo.description}</span>
                            <span>Forks : {repo.forks}</span>
                            <span><a href={repo.html_url}>{repo.html_url}</a></span>
                            <span>Created at : {repo.created_at}</span>
                            <span>Size : {repo.size}</span>
                            <span>Language : {repo.language}</span>
                            <span>Watchers : {repo.watchers}</span>
                        </p>
                    </div>
                    )
                } 
                
            </div>
        );
    }

    render(){
        let contents = this.state.loading
        ? <p><em>loading informations ...</em></p>
        : UserInformation.renderInformations(this.state.informations);

        return(
            <div>
                {contents}
            </div>
        );
    }

    async populateInformations(){
        const response = await fetch('https://localhost:5001/base/elwin013/1');
        //const generated_uri = 'https://localhost:5001/base/'+this.props.location.state.username+'/'+this.props.location.state.depth;
        //const response = await fetch(generated_uri);
        const data = await response.json();
        this.setState({ informations: data, loading:false})
    }
    


}