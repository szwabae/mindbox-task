#!/bin/sh

TAGS=".tags"
DEPLOYMENT="dev/mindbox-task-dev.yaml"

if ! [ -f $TAGS ]; then
	echo "$TAGS not found."
	exit 1;
fi

TAG=$(cat .tags)

cd ..

apk add git
git clone https://github.com/szwabae/mindbox-k8s.git

cd mindbox-k8s

if ![ -f $DEPLOYMENT ]; then
	echo "$DEPLOYMENT not found."
	exit 1;
fi

sed -i "s|\(image: szwabaewa/mindbox-task\):.*$|\1:$TAG|" $DEPLOYMENT

git add $DEPLOYMENT
git commit -m "[DRONE.io] k8s update deployment"
git remote add authenticated-origin https://szwabae:${GITHUB_API_KEY}@github.com/szwabae/mindbox-k8s
git fetch authenticated-origin
git push authenticated-origin master