Set WshShell = CreateObject("WScript.Shell") 
Set fso = CreateObject ("Scripting.FileSystemObject")
Set stdout = fso.GetStandardStream (1)
WshShell.Run chr(34) & "start-server.bat" & Chr(34), 0
Set WshShell = Nothing