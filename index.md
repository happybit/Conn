---
layout: index
---

# Intro

[**Conn**](http://conn.pzheng.me), with Android material design interface, aims to connect mobile data with only one click in poor quality signal situation for Android devices. No need to manually connect/disconnect the data network over and over again. Neither to acknowledge the annoying warning dialog from most manufactures about incurring addition charges everytime you try to connect data network.

# Notice

1. For devices with Android Lollipop or above, it requires root privilege since the method working on pre-Lollipop to enable/disable mobile data has been [deprecated](https://code.google.com/p/android/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Owner%20Summary%20Stars&groupby=&sort=&id=78084) from Android 5.0 API.
2. In order to reteive/change network state, you need to grant below permissions:
  * CHANGE_NETWORK_STATE;
  * ACCESS_NETWORK_STATE;
  * READ_PHONE_STATE;
  * INTERNET;

# Usage

1. Click the floating button on the lower right corner to connect mobile data network. A notificaiton may be prompted if the phone has already connected to network via WiFi or in Airplane mode;
2. Or you can just add the widget to your home sceen which might be more convenient;
3. "On"/"Off" button will allow you to connect/disconnect mobile data for once;
4. In "Settings", you can set delay between retries as well as maximum retry times. We recommend 10 seconds delays and max 10 times retries on average.
5. Sometimes the device might be fell into "Zombie Network" state, which means it cannot access to the internet even though the data network appears "connected". You can enable the function in "Settings" to verify the connectivity by ping the website. Now [Baidu](http://www.baidu.com) and [Google](http://www.google.com) are supported. Please notice _you may incur additional charges when using this funcion_.

# Screenshots

![conn_connect](https://dl.dropboxusercontent.com/u/6459697/blogimage/20150402_conn_connect.jpg)

![conn_settings](https://dl.dropboxusercontent.com/u/6459697/blogimage/20150402_conn_settings.jpg)

![conn_about](https://dl.dropboxusercontent.com/u/6459697/blogimage/20150402_conn_about.jpg)

![conn_drawer](https://dl.dropboxusercontent.com/u/6459697/blogimage/20150402_conn_drawer.jpg)

# Credits

## FloatingActionButton

[https://github.com/makovkastar/FloatingActionButton](https://github.com/makovkastar/FloatingActionButton)

The MIT License (MIT)

Copyright (c) 2014 Oleksandr Melnykov

## android-material-drawer-template

[https://github.com/kanytu/android-material-drawer-template](https://github.com/kanytu/android-material-drawer-template)

Copyright (c) 2014 Pedro Oliveira

Licensed under the Apache License, Version 2.0

# License

The MIT License (MIT)

Copyright (c) 2012-2015 [pzheng.me](http://www.pzheng.me)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

# ChangeLog

## v1.0.0

**April/2/2015**

  * First release;
