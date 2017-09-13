#!/usr/bin/env bash

# add main repo as an upstream remote so that we can pull lastest from main repo
echo "* Add main repository as upstream remote"
git remote add upstream git@github.com:ETDA/soda-etax.git

# add commit template to git config
echo "* Add git commit guideline into commit template"
git config --local commit.template .gitmessage
