##Android Building blocks

- Activities
- Fragments
- Services
- Broadcast Receiver


## Activity Lifecycle

- FIRST TIME ENTERS 
onCreate --> onStart --> onResume5

- WHEN USER GOES TO BACKGROUND 
onPause --> onStop

- WHEN USER COMES BACK TO APP 
onRestart --> onStart --> onResume

## Fragment Lifecycle
onAttach --> onCreate --> onCreateView --> onActivityCreated --> onStart --> onResume
--> onPause --> onStop -> onDestroyView((Recreated from backstack with onCreateView) else -->) --> onDestroy --> onDetach
  
## Content provider
allows connectivity between our app and structured data (device data)
can access data using CONTENT RESOLVER as URI from OTHER APPS as Cursor
we can also expose our data to other apps with content URIs

## Service
to run some tasks but it runs in MAIN THREAD
for worker thread(asynchronous) use INTENT SERVICE
- service send BROADCAST to ANDROID OS and we receive that BROADCAST to our app it will receive in onReceive method


## Configuration changes
- Device Rotation
- User takes a phone call
- phone notifications
- battery changes