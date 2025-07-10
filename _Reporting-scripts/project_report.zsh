cd ..
rm _scripts/project-report_mvn-site-output.txt
mvn clean site >> _scripts/project-report_mvn-site-output.txt && cd target/site/ && open index.html