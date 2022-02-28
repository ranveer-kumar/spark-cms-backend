package com.spark.cms.resolver;

import com.spark.cms.constants.Constants;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class UploadFileMutationResolver implements GraphQLMutationResolver {
    @Value("${base.file.path}")
    private String baseFilePath;

    public String uploadFile(DataFetchingEnvironment dataFetchingEnvironment){
        DefaultGraphQLServletContext context = dataFetchingEnvironment.getContext();


        context.getFileParts().forEach(
                part -> {
                    try {

                        String filePath = baseFilePath + File.separator + part.getSubmittedFileName();
                        part.write(filePath);

                    } catch (IOException e) {
                        log.error("Error in file upload {}", e.getMessage());
                    }
                    log.info("uploading: {} , size: {} , name: {}", part.getSubmittedFileName(), part.getSize(), part.getSubmittedFileName());
                }

        );
        return  "File uploaded successfully!";
    }
}
