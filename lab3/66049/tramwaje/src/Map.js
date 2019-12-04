import React from 'react';
import { Component } from 'react';
import ReactMapGL, { Marker } from 'react-map-gl';

class Map extends Component {

    state = {
        viewport: {
            width: window.innerWidth,
            height: window.innerHeight,
            latitude: 52.237049,
            longitude: 21.017532,
            zoom: 13,
            maxZoom: 16,
            minZoom: 12,
            mapboxApiAccessToken: process.env.REACT_APP_MAPBOX_TOKEN,
        }
    }
    updateViewport = (viewport) => {
        this.setState({viewport});
    };

    renderFoodtruckMarker = () => {
        
    } 

    render() {
        const {viewport} = this.state;
        return (
            <div>
                <ReactMapGL
                {...this.state.viewport}
                onViewportChange={this.updateViewport}
                >

                </ReactMapGL>
            </div>
        );
    }
}

export default Map;