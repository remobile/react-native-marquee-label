/*
* (The MIT License)
* Copyright (c) 2015-2016 YunJiang.Fang <42550564@qq.com>
* @providesModule MarqueeLabel
* @flow-weak
*/
'use strict';

var React = require('react-native');
var {
    PropTypes,
    requireNativeComponent
} = React;

var MarqueeLabel = React.createClass({
    propTypes: {
        text : PropTypes.string.isRequired,
        marqueeType : PropTypes.number,
        scrollDuration : PropTypes.number,
        fadeLength : PropTypes.number,
        leadingBuffer : PropTypes.number,
        trailingBuffer : PropTypes.number,
        textColor : PropTypes.string,
        font: PropTypes.string
    },
    render: function() {
        return (
            <RCTMarqueeLabel  {...this.props}/>
        );
    }
});

var RCTMarqueeLabel = requireNativeComponent('RCTMarqueeLabel', MarqueeLabel);
module.exports = MarqueeLabel;
