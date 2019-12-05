import React, { Component } from 'react';
import ReactMapGL, { Marker, Popup } from 'react-map-gl';
import './App.css';
import { throwStatement } from '@babel/types';

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
        },
        trams: [],
        selectedTram: null
    }

    componentDidMount() {
        this.fetchData();
        setInterval(this.fetchData, 10000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    } 

    fetchData = () => {
       fetch('https://api.um.warszawa.pl/api/action/busestrams_get/?resource_id=f2e5503e927d-4ad3-9500-4ab9e55deb59&apikey=b183d804-0cca-49a0-96ef-09e04c53b5f5&type=2')
       .then(res => res.json())
       .then(result => {
           this.setState({
               trams: result.result
           });
           console.log(result.result);
       });
    }

    updateViewport = (viewport) => {
        this.setState({viewport});
    };

    setSelectedTram = object => {
        this.setState({
            selectedTram: object
        });
    }

    loadTrams = () => {
        return this.state.trams.map(tram => {
            return (
                <Marker key={tram.Brigade} latitude={parseFloat(tram.Lat)} longitude={parseFloat(tram.Lon)}>
                    <div className='LineNumber'>{tram.Lines}</div>
                    <img className='icon' src='/icon.png' onClick={() => { this.setSelectedTram(tram); }}/>
                </Marker>
            );
        });
    }

    closePopup = () => {
        this.setState({selectedTram: null});
    }

    render() {
        const {viewport} = this.state.viewport;
        return (
                <ReactMapGL
                {...this.state.viewport}
                onViewportChange={this.updateViewport}
                >
                {this.loadTrams()}
                {this.state.selectedTram !== null ? (
                    <Popup 
                        latitude={parseFloat(this.state.selectedTram.Lat)}
                        longitude={parseFloat(this.state.selectedTram.Lon)}
                        onClose={this.closePopup}
                        onRequestClose={this.closePopup}>
                            <div>
                                {this.state.selectedTram.Brigade}
                            </div>
                    </Popup>
                ) : null}
                </ReactMapGL>
        );
    }
}

export default Map;