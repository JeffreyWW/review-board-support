package com.guyazhou.tools.plugin.reviewboard.vcsprovider;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.changes.Change;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.List;

/**
 * VCS抽象方法
 * Created by Yakov on 2016/12/30.
 */
public abstract class AbstractVcsProvider implements VcsProvider {

    protected AbstractVcs abstractVcs;
    protected String differences;
    protected String repositoryURL;
    protected String workingCopyPathInRepository;
    protected String workingCopyDir;
    protected List<Change> changeList;

    /**
     * Construction
     * Set vcs
     * @param abstractVcs vcs
     */
    protected AbstractVcsProvider(AbstractVcs abstractVcs) {
        this.abstractVcs = abstractVcs;
    }

    @Override
    public AbstractVcs getVCS() {
        return this.abstractVcs;
    }

    /**
     * Build
     * Get repository root, and generate diff
     * @param project current project
     * @param virtualFiles selected files
     */
    @Override
    public void build(Project project, List<VirtualFile> virtualFiles) {
        try {
            this.setRepositoryRootAndWorkingCopyPath(project, virtualFiles);
        } catch (Exception e) {
            throw new RuntimeException("Get repository root and working copy path error, " + e.getMessage());
        }
        try {
            this.differences = generateDifferences(project, virtualFiles);
        } catch (Exception e) {
            throw new RuntimeException("Generate differences error, " + e.getMessage());
        }
    }

    @Override
    public String getDifferences() {
        return this.differences;
    }

    @Override
    public String getRepositoryURL() {
        return this.repositoryURL;
    }

    @Override
    public String getWorkingCopyPathInRepository() {
        return this.workingCopyPathInRepository;
    }

    /**
     * Set repository root url and working copy path in repository according to the given selected virtual files
     * @param project project
     * @param virtualFiles selected files
     */
    protected abstract void setRepositoryRootAndWorkingCopyPath(Project project, List<VirtualFile> virtualFiles) throws Exception;

    /**
     * Generate differences between local and remote repository
     * @param project current project
     * @param virtualFiles virtural files
     * @return diff string
     */
    protected abstract String generateDifferences(Project project, List<VirtualFile> virtualFiles) throws Exception;

}
