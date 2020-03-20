
# react-native-network-info

## Getting started

`$ npm install react-native-network-info-lad --save`

### Mostly automatic installation

`$ react-native link react-native-network-info-lad`

### Manual installation



#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNNetworkInfoPackage;` to the imports at the top of the file
  - Add `new RNNetworkInfoPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-network-info'
  	project(':react-native-network-info').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-network-info/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-network-info')
  	```

## Usage
```javascript
import {  RNNetworkInfo } from 'react-native-network-info-lad';
(async ()=>{
  const data = await NetworkInfo.getNetworkInfo();
  console.log('NetworkInfo',data);
})();

```
  