DIR="_Reporting-scripts"

cd ..
rm "$DIR/project-report_mvn-site-output.txt"
mvn clean site >> "$DIR/project-report_mvn-site-output.txt" && \
cd target/site/ && \
open index.html