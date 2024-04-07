FROM harbor.bsfit.com.cn/base/jdk:8u231

LABEL MAINTAINER="zhangyafei" \
timestamp="20220617 9:00"


ENV LANG="en_US.UTF-8" \
LANGUAGE="en_US.UTF-8" \
LC_ALL="en_US.UTF-8" \
name=bsfit-rgas-mgr

WORKDIR /app/$name

COPY . .

EXPOSE 7080

ENTRYPOINT ["/bin/sh","k8s_run.sh"]
