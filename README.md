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
  

## Configuration changes
- Device Rotation
- User takes a phone call
- phone notifications
- battery changes