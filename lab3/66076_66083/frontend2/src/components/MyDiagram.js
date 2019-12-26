import React from 'react';
import Tree from 'react-tree-graph';
import 'react-tree-graph/dist/style.css'

class MyDiagram extends React.Component {
    data = {
        name: 'Parent',
        children: [{
            name: 'Child One'
        }, {
            name: 'Child Two'
        }]
    }

    render() {
        return (
            <Tree
                data={this.data}
                height={400}
                width={400}/>
        )
    }
}
export default MyDiagram;