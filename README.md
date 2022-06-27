
<h1 align="center">MarvelComicsApp</h1>
<p align="center">

  <img width="230" height="150" src="https://www.pngitem.com/pimgs/m/33-334275_clip-art-marvel-comics-logo-marvel-comics-logo.png">
</p>

<p align="center">
Android App with Retrofit, Hilt, MVVM and Clean Architecture concepts integrated with MarvelAPI.  <br> <br>
The app displays a list of comics by consuming the marvel api. On clicking any list item it opens the details screen. 
You can go to the official marvel comics website by clicking on <strong><em>Find out more</strong></em>.
On search screen you can explore the comics by typying title name.
</p>


## Tech stack & Open-source libraries
- Mimimum SDK level 21
- <a href="https://kotlinlang.org/" target="_blank">Kotlin</a> based, <a href="https://github.com/Kotlin/kotlinx.coroutines" target="_blank">Coroutines</a> + <a href="https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/" target="_blank">Flow</a> for asynchronous.
- <a href="https://developer.android.com/jetpack/compose" target="_blank">Jetpack Compose</a>
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- <a href="https://m3.material.io/" target="_blank">Material 3</a>
- **MVVM Architecture**   (View - DataBinding - ViewModel - Model)
- <a href="https://dagger.dev/hilt/" target="_blank">Hilt</a> - dependency injection
- <a href="https://github.com/square/retrofit" target="_blank">Retrofit2 & OkHttp3</a> - construct the REST APIs
- <a href="https://coil-kt.github.io/coil/compose/" target="_blank">Coil</a> - image loading

## Jetpack Compose 

Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

## Coil

Coil is a new image loading framework on Android, using many advanced features like coroutines, OkHttp, and androidX.lifecycles. In addition to simply loading images, it also includes several advanced features, like image sampling, effective memory usage, and automatic canceling/pausing of requests. Coil also includes artifacts for supporting gif, svg, and video formats.
## Open API

MarvelComicsApp using the <a href="https://www.marvel.com/comics" target="_blank">MarvelAPI</a> for constructing RESTful API.
MarvelAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Marvel Comics.

## Demo

![](https://github.com/KlaudiaK/MarvelComicsApp/blob/main/app-gif.gif)

