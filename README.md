# React Native MarqueeLabel (remobile)
A marquee label for react-native
* if you use js pure marquee, use remobile/react-native-marquee[ https://github.com/remobile/react-native-marquee ]

## Installation
```sh
npm install @remobile/react-native-marquee-label --save
```

### Installation (iOS)
* Drag RCTMarqueeLabel.xcodeproj to your project on Xcode.
* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTMarqueeLabel.a from the Products folder inside the RCTMarqueeLabel.xcodeproj.
* Look for Header Search Paths and make sure it contains both $(SRCROOT)/../../../react-native/React as recursive.

### Installation (Android)
```gradle
...
include ':react-native-marquee-label'
project(':react-native-marquee-label').projectDir = new File(settingsDir, '../node_modules/@remobile/react-native-marquee-label/android/RCTMarqueeLabel')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':react-native-marquee-label')
}
```

* register module (in MainApplication.java)

```java
......
import com.remobile.marqueeLabel.RCTMarqueeLabelPackage;  // <--- import

......

@Override
protected List<ReactPackage> getPackages() {
   ......
   new RCTMarqueeLabelPackage(),            // <------ add here
   ......
}

```

## Usage

### Example
```js
'use strict';

var React = require('react');
var ReactNative = require('react-native');
var {
    StyleSheet,
    View
} = ReactNative;

var MarqueeLabel = require('@remobile/react-native-marquee-label');

module.exports = React.createClass({
    render: function() {
        return (
            <View style={styles.container}>
                <MarqueeLabel style={styles.marqueeLabel}
                    scrollDuration={3.0}
                    >
                    fangyunjiang is a good developer
                </MarqueeLabel>
            </View>
        );
    }
});

var styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },

    marqueeLabel: {
        backgroundColor: 'blue',
        width:260,
        height:200,
        fontSize:30,
        fontWeight:'800',
        color:'white',
    }
});
```
### Screencasts

![loading](https://github.com/remobile/react-native-marquee-label/blob/master/screencasts/demo.gif)

### Props
- `text : PropTypes.string.isRequired`
- `scrollDuration : PropTypes.number` //seconds
- `marqueeType : PropTypes.string` //ios
- `fadeLength : PropTypes.number` //ios
- `leadingBuffer : PropTypes.number` //ios
- `trailingBuffer : PropTypes.number` //ios
- `isRepeat : PropTypes.bool` //android
- `startPoint : PropTypes.number` //android
- `direction : PropTypes.number` //android

### see detail use
* https://github.com/remobile/react-native-template
