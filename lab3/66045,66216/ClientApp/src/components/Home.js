import React, { Component } from 'react';
import { Redirect } from 'react-router';

export class Home extends Component {
    handleSubmit(uname,number){
    console.log("ASD");
    return(
    <Redirect to ={{
      pathname: '/user',
      state: {username: this.uname, 
              depth: this.number}
    }} />
    )
  }

  render () {
    return (
        <form>
         <fieldset>
         <select name="numberlist" class="numbers">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
          </select>
            <label>User name : 
              <input type = "text"
                  name = "username"
                  defaultValue = ""/>
            </label>
           <button type="button" onClick={this.handleSubmit('elwin013', 1)}>Submit</button>
        </fieldset>
        </form>
      
    )
  }
}
