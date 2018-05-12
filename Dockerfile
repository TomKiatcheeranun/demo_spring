FROM java:8u111-jre

RUN mkdir -p /project
RUN mkdir -p /image_info
RUN mkdir -p /usr/script/
COPY startup.sh /usr/script/
COPY #APP_NAME#-#APP_VERSION#.jar /project
RUN chmod -R 775 /usr/script/

RUN echo "#APP_NAME#" > /image_info/app_name && echo "#APP_VERSION#" > /image_info/app_version

CMD ["/usr/script/startup.sh"]
