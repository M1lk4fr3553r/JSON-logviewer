# JSON-logviewer
## Command line arguments
`JSON-logviewer [file]`  
Launches the logviewer and optionally loads a file

## Hotkeys
`l`: Load a file  
`f`: Filter entries  
`s`: Search in entries  

## Options
### Log-Keys
- `key.timestamp=@timestamp`: Sets the timestamp-key
- `key.level=level`: Sets the level-key
- `key.message=message`: Sets the message-key

### Colors
- `color.info=#000000`: Sets the info color
- `color.warn=#ada401`: Sets the warning color
- `color.error=#aa0000`: Sets the error color
- `color.debug=#0037dd` Sets the debug color

### Other
- `path.last=`: Stores the last opened path, you generally dont need to touch this option
