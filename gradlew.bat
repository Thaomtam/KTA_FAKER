@echo off
set JAVA_CMD=java
set JAR_PATH=gradle\wrapper\gradle-wrapper.jar
%JAVA_CMD% -jar %JAR_PATH% %*
