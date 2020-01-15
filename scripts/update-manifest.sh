#!bin/sh

TAGS=".tags"
DEPLOYMENT="mindbox-task-dev.yaml"

if ![ -f $TAGS ]; then
	echo "$TAGS not found."
	exit 1;
fi

if ![ -f $DEPLOYMENT ]; then
	echo "$DEPLOYMENT not found."
	exit 1;
fi

TAG=$(cat .tags)

sed -i "s|\(image: szwabaewa/mindbox-task\):.*$|\1:$TAG|" $DEPLOYMENT
apk add git
git add $DEPLOYMENT
git commit -m "[DRONE.io] k8s update deployment [ci skip]"
git remote add authenticated-origin https://szwabae:${GITHUB_API_KEY}@github.com/szwabae/mindbox-task
git fetch authenticated-origin
git push authenticated-origin master