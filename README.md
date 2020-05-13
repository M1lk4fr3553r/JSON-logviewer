# JSON-logviewer
## Command line arguments
`JSON-logviewer [file]`  
Launches the logviewer and optionally loads a file

## Hotkeys
`CTRL + O`: Load a file  
`CTRL + F`: Filter entries  
`CTRL + S`: Search in entries  

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
- `color.search=#9c9e67` Sets the search highlight color

### Other
- `path.last=`: Stores the last opened path, you generally don't need to touch this option
