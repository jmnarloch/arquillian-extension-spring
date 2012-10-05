/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.spring.deployer.dependency;

import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import java.io.File;

/**
 * <p>A helper class for resolving dependency using maven.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 * @version $Revision: $
 */
public class MavenDependencyBuilder {

    /**
     * <p>The dependencies map.</p>
     */
    private final MavenDependencyResolver mvnDependencyResolver;

    /**
     * <p>Creates new instance of {@link MavenDependencyBuilder}.</p>
     */
    public MavenDependencyBuilder() {

        mvnDependencyResolver = DependencyResolvers.use(MavenDependencyResolver.class);
    }

    /**
     * <p>Adds the dependency.</p>
     *
     * @param artifactName    the artifact name
     * @param artifactVersion the artifact version
     * @param defaultVersion  the artifact default version
     * @param exclusions      the names of the artifact which need to excluded during artifact resolving
     */
    public void addDependency(String artifactName, String artifactVersion, String defaultVersion, String... exclusions) {

        resolveArtifact(artifactName, artifactVersion, defaultVersion, exclusions);
    }

    /**
     * <p>Retrieves the dependencies.</p>
     *
     * @return the list of dependencies
     */
    public File[] getDependencies() {

        return mvnDependencyResolver.resolveAsFiles();
    }

    /**
     * <p>Resolves the artifact using the given version, if the version hasn't been specified than the default artifact
     * version will be used.</p>
     *
     * @param artifact       the artifact name
     * @param version        the artifact version
     * @param defaultVersion the default artifact version to be used
     * @param exclusions     the names of the artifact which need to excluded during artifact resolving
     */
    private void resolveArtifact(String artifact, String version, String defaultVersion, String... exclusions) {
        String artifactVersion;

        if (version != null && version.length() > 0) {
            artifactVersion = version;
        } else {
            artifactVersion = defaultVersion;
        }

        resolveArtifact(artifact, artifactVersion, exclusions);
    }

    /**
     * <p>Resolves the given artifact in specified version using maven.</p>
     *
     * @param artifact   the artifact name
     * @param version    the artifact version
     * @param exclusions the names of the artifact which need to excluded during artifact resolving
     */
    private void resolveArtifact(String artifact, String version, String... exclusions) {

        resolveArtifact(artifact + ":" + version, exclusions);
    }

    /**
     * <p>Resolves the given artifact in specified version using maven.</p>
     *
     * @param artifact   the artifact name
     * @param exclusions the names of the artifact which need to excluded during artifact resolving
     */
    private void resolveArtifact(String artifact, String... exclusions) {

        mvnDependencyResolver.artifacts(artifact).exclusions(exclusions);
    }
}
