import React, { Component } from 'react'
import { Route } from 'react-router'
import { Layout } from './components/Layout'
import { Home } from './components/Home'
import './custom.css'
import { UserInformation } from './components/UserInformation'

const user = "elwin013" ;
const depth = "4"; 
const url = "https://localhost:5001/base/"

async function getData() {
  const reponse = await fetch(url+user+"/"+depth)
  const data =await reponse.json()
}

export default class App extends Component {
  static displayName = App.name;
  render(){ //routing
    return(
      <Layout>
      <Route exact path='/' component={Home} />
      <Route path='/user' component={UserInformation} />
      {/* <Route path='/graph' component={Graph} /> */}
      </Layout>
    );
  }
}