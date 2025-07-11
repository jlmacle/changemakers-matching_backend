cd ..
rm _Reporting-scripts/project-report_mvn-site-output.txt
mvn clean site >> _Reporting-scripts/project-report_mvn-site-output.txt && \
cd target/site/ && \
open index.html