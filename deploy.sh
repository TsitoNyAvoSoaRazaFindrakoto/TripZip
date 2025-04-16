#!/bin/bash

# Load environment variables from .env file
while IFS='=' read -r key value; do
    if [[ ! $key =~ ^#.*$ && -n $key ]]; then
        export "$key=$value"
    fi
done < .env

project_name="$PROJECT_NAME"
work_dir="$WORK_DIR/$PROJECT_NAME"
lib="$work_dir/lib"
bin="$work_dir/bin"
web="$work_dir/web"
web_xml="$work_dir/config"
temp="$work_dir/.temp"

war_file="$work_dir/$project_name.war"
destination="$TOMCAT_WEBAPPS/"

echo "$project_name"
echo "$work_dir"
echo "$destination"

# --- CREATE TEMP FOLDER ---
# Delete temp folder if it exists
if [ -d "$temp" ]; then
    echo "deleting temp folder"
    rm -rf "$temp"
    # echo "deleting bin folder"
    # rm -rf "$bin"
fi

# echo "compilation"
# Call compilation script
# ./compilateur.sh
# echo "compilation done"

mkdir -p "$temp"
cp -r "$web/"* "$temp/"
mkdir -p "$temp/WEB-INF/classes"
cp -r "$bin/" "$temp/WEB-INF/classes/"
cp -r "$web_xml/"* "$temp/WEB-INF/"
mkdir -p "$temp/WEB-INF/lib"
cp -r "$lib/"* "$temp/WEB-INF/lib/"

cd "$temp"
rm -f "$war_file"
jar cf "$war_file" .

rm -f "$destination$project_name.war"
cp "$war_file" "$destination"
rm -f "$war_file"