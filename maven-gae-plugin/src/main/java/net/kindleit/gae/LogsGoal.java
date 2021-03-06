/* Copyright 2011 Kindleit.net Software Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kindleit.gae;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Retrieves logs from Google's servers.
 *
 * @author jpeynado@kindleit.net
 * @goal logs
 * @requiresOnline
 */
public class LogsGoal extends EngineGoalBase {

  /** Log report output file.
   *
   * @parameter expression="${project.build.directory}/gae.log"
   */
  protected File outputFile;

  /** Number of days to retrieve from the log.
   *
   * @parameter expression="${gae.log.days}" default-value="1"
   */
  protected int days;

  /** Severity Log level to retrieve.
   *
   * @parameter expression="${gae.log.severity}"
   */
  protected Integer severity;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Getting application logs...");
    runAppCfg("request_logs", appDir, outputFile.toString());
  }
  
  @Override
  protected List<String> getAppCfgArgs() {
    final List<String> args = getCommonArgs();
    
    args.add("--num_days=" + days);
    if (severity != null) {
      args.add("--severity=" + severity);
    }
    
    return args;
  }

}
