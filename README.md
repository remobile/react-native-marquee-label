# React Native MarqueeLabel (remobile)
A marquee label for react-native

## Installation
```sh
npm install @remobile/react-native-marquee-label --save
```

### Installation (iOS)
* Drag RCTMarqueeLabel.xcodeproj to your project on Xcode.
* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTMarqueeLabel.a from the Products folder inside the RCTMarqueeLabel.xcodeproj.
* Look for Header Search Paths and make sure it contains both $(SRCROOT)/../react-native/React as recursive.

### Installation (Android)
```gradle
...
include ':react-native-marquee-label'
project(':react-native-marquee-label').projectDir = new File(rootProject.projectDir, '../node_modules/@remobile/react-native-marquee-label/android/RCTMarqueeLabel')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':react-native-marquee-label')
}
```

* register module (in MainActivity.java)

```java
import com.remobile.marqueeLabel.*;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new RCTMarqueeLabelPackage())              // <------ add here
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "ExampleRN", null);

    setContentView(mReactRootView);
  }

  ......

}
```

## Usage

### Example
```js
'use strict';

var React = require('react-native');
var {
    StyleSheet,
    View
} = React;

var MarqueeLabel = require('react-native-marquee-label');
module.exports = React.createClass({
    render: function() {
        return (
            <View style={styles.container}>
                <MarqueeLabel style={styles.marqueeLabel}
                     text="fangyunjiang is a good developer"
                     marqueeType="MLContinuous"
                     scrollDuration={3.0}
                     fadeLength={0.0}
                     leadingBuffer={0.0}
                     trailingBuffer={50}
                     textColor='red'
                     font={{fontSize:80, fontWeight: 0.4}}
                     />
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
        backgroundColor: '#FFFFE0',
        width:200,
        height:140
    }
});
```
### Screencasts

![loading](https://github.com/remobile/react-native-marquee-label/blob/master/screencasts/demo.gif)

### Props
- `text : PropTypes.string.isRequired`
- `marqueeType : PropTypes.number`
- `scrollDuration : PropTypes.number`
- `fadeLength : PropTypes.number`
- `leadingBuffer : PropTypes.number`
- `trailingBuffer : PropTypes.number`
- `textColor : PropTypes.string`
- `font: PropTypes.string`
