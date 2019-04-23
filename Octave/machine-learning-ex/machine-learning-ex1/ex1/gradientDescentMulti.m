function [theta, J_history] = gradientDescentMulti(X, y, theta, alpha, num_iters)
%GRADIENTDESCENTMULTI Performs gradient descent to learn theta
%   theta = GRADIENTDESCENTMULTI(x, y, theta, alpha, num_iters) updates theta by
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters

    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCostMulti) and gradient here.
    %

    n = length(theta);
    theta_m = zeros(n,1);
    theta_t = zeros(n,1);
    for i = 1:m
      h = X(i,:) * theta ;% 计算h
      for j = 1:n
        theta_m(j,1) = theta_m(j,1) + (h - y(i)) * X(i,j); % 计算代价并累加
      endfor
    endfor
    for j = 1:n
      theta_t(j,1) = theta(j,1) - alpha*theta_m(j,1)/m;
    endfor
    for j = 1:n
      theta(j,1) = theta_t(j,1);
    endfor 


    % ============================================================

    % Save the cost J in every iteration    
    J_history(iter) = computeCostMulti(X, y, theta);

end

end
